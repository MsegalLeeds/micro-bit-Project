import com.sun.net.httpserver.HttpServer
import java.io.File
import java.net.InetSocketAddress
import java.net.URLDecoder

private val reading = Regex("""([A-Za-z0-9_]+),([+-]?[0-9]+(?:\.[0-9]+)?),([A-Za-z0-9_]+)""")

private fun escape(text: String) = text.replace("&", "&amp;")
    .replace("<", "&lt;").replace(">", "&gt;")

fun dashboard(input: String): String {
    val rows = StringBuilder()
    val errors = mutableListOf<Int>()
    input.lines().forEachIndexed { index, line ->
        if (line.isNotBlank()) {
            val match = reading.matchEntire(line.trim())
            if (match == null) {
                errors.add(index + 1)
            } else {
                val (metric, value, unit) = match.destructured
                rows.append("<tr><td>$metric</td><td>$value</td><td>$unit</td></tr>")
            }
        }
    }
    val feedback = if (errors.isEmpty()) "" else
        "<p>Check the format on lines ${errors.joinToString()}.</p>"
    return """
        <!doctype html><html lang="en"><meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>micro:bit readings</title>
        <style>body{font:1rem system-ui;max-width:48rem;margin:2rem auto;padding:0 1rem}
        textarea{width:100%;box-sizing:border-box}td,th{padding:.4rem 1rem;text-align:left}</style>
        <main><h1>micro:bit readings</h1>
        <form action="/" method="get"><label for="data">Paste one metric,value,unit reading per line</label>
        <textarea id="data" name="data" rows="5">${escape(input)}</textarea>
        <button>Display readings</button></form>$feedback
        <table><caption>Device readings</caption><thead><tr><th>Metric</th><th>Value</th><th>Unit</th></tr></thead>
        <tbody>$rows</tbody></table></main></html>
    """.trimIndent()
}

fun main() {
    val sample = File("../shared/sample.csv").readText()
    val server = HttpServer.create(InetSocketAddress("127.0.0.1", 8080), 0)
    server.createContext("/") { request ->
        request.use {
            val data = request.requestURI.rawQuery?.split('&')
                ?.firstOrNull { it.startsWith("data=") }?.removePrefix("data=")
            val input = data?.let { URLDecoder.decode(it, Charsets.UTF_8) } ?: sample
            val body = dashboard(input).toByteArray()
            request.responseHeaders.set("Content-Type", "text/html; charset=utf-8")
            request.sendResponseHeaders(200, body.size.toLong())
            request.responseBody.write(body)
        }
    }
    server.start()
    println("Dashboard running at http://127.0.0.1:8080")
}
