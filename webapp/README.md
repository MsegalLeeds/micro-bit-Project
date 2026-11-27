# Dashboard team

Install JDK 17 or 21 and Gradle 8.14 or 9.0. From the repository root:

```sh
cd webapp
gradle run
```

Open <http://127.0.0.1:8080>. The first build downloads the Kotlin plugin. You can also open this directory as a Gradle project in IntelliJ IDEA and run the Gradle `application → run` task.

The page starts with two rows from [sample.csv](../shared/sample.csv). Paste `temperature,-2.5,C` in the input and select **Display readings**. You should see one temperature row. Try `bad,line` to check the error feedback. Updating the page affects only that browser request.

Start in [Dashboard.kt](src/main/kotlin/Dashboard.kt) to change the Kotlin parser or HTML view. The server listens on the local computer. Stop it with Ctrl+C.
