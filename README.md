# intellij-invokeLater-plugin

Intellij inspection plugin that points `SwingUtilities#invokeLater` usages as erroneous in Java code and suggests to replace code to `Application#invokeLater`.


## Motivation
In IntelliJ code base it’s prohibited to use SwingUtilities#invokeLater , 
mainly because it could be too late to perform the runnable but there is no convenient API to check if that's the case (like the initial dialog is still shown, no progress window appeared since the activity was requested, etc.)

## How to run

You can run the plugin in debug mode by using gradle's `runIde` task, which is part of [intellij plugin](https://github.com/JetBrains/gradle-intellij-plugin)
