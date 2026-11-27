# micro:bit student project

First-year students build the micro:bit project. Second-year students build the Kotlin web app that displays its readings.

| If you're working on | Start here |
| --- | --- |
| Device code | [microbit/](microbit/README.md) — flash the example and capture its output |
| Web app | [webapp/](webapp/README.md) — where level 2s develop webapp |
| Data passed between them | [shared/](shared/README.md) — reading format and sample output |

## Make your first contribution

1. Clone the repository with `git clone https://github.com/MsegalLeeds/micro-bit-Project.git` and run `cd micro-bit-Project`.
2. Start from the latest main branch with `git switch main` and `git pull --ff-only`. Create a branch for your change, for example `git switch -c microbit/button-counter`.
3. Edit your team's files and try the checks in its README. If you change the reading format, update the shared example and coordinate with the other team.
4. Run `git status`, stage the files you changed with `git add <path>`, then commit with a short message describing the change.
5. Push with `git push -u origin HEAD`. On GitHub, open a pull request into `main`, describe how you tested it and ask a teammate to review.

A good first contribution is to add a device reading or improve how the dashboard displays the sample. Each team can work independently using the shared example.
