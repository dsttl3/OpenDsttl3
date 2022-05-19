# jpackage 用法

## 通用选项:

```  @<filename ```

        Read options and/or mode from a file
          This option can be used multiple times.

 ``` --type -t <type```

        创建有效值的包类型是:{"app-image"， "exe"， "msi"}如果没有指定此选项，将创建一个平台相关的默认类型。

 ``` --app-version <version ```

        应用程序和/或包的版本

 ``` --copyright <copyright string ```

        申请的版权

```  --description <description string ```

        应用的描述

```  --help -h ```

        将包含当前平台的每个有效选项的列表和描述的用法文本打印到输出流，然后退出。

```  --icon <file path ```

        应用程序包图标的路径 (绝对路径或相对于当前目录的路径)

```  --name -n <name ```

        应用程序和/或包的名称

```  --dest -d <destination path ```

         生成的输出文件所在的路径(绝对路径或相对于当前目录的路径)默认为当前工作目录。

```  --temp <directory path ```

        用于创建临时文件的新目录或空目录的路径(相对于当前目录的绝对路径或相对路径)。如果指定此路径，任务完成时将不会删除临时目录，必须手动删除。如果未指定，将在任务完成时创建并删除临时目录。

```  --vendor <vendor string ```

        应用程序供应商

```  --verbose ```

        可以详细的输出

```  --version ```

        将产品版本打印到输出流并退出。

## 用于创建运行时映像的选项:

```  --add-modules <module name[,<module name...] ```

        用逗号(“，”)分隔要添加的模块列表
        此模块列表，以及主模块(如果指定)
        将作为——add-module参数传递给jlink。
        如果没有指定，则只指定主模块(If——module is .)指定的)，或默认的模块集(如果——main-jar是
        使用指定)。
        此选项可以多次使用。

```  --module-path -p <module path... ```

        一个;分离的路径列表
        每个路径要么是一个模块目录，要么是模块化的jar。
        (每个路径都是相对于当前目录的绝对路径或相对路径。)
        此选项可以多次使用。

```  --jlink-options <jlink options ```

        要传递给jlink的以空格分隔的选项列表
        如果未指定，默认为"——strip-native-commands "
        ——strip-debug no-man-pages——no-header-files”。
        此选项可以多次使用。

```  --runtime-image <directory path ```

        要复制到其中的预定义运行时映像的路径
        应用程序映像
        (绝对路径或相对于当前目录)
        如果没有指定——runtime-image, jpackage将运行jlink to
        使用以下选项创建运行时映像:
        ——strip-debug，——no-header-files，——no-man-pages，和
        ——strip-native-commands。

## 创建应用程序映像的选项:

```  --input -i <directory path ```

        包含要打包文件的输入目录的路径
        (绝对路径或相对于当前目录)
        输入目录中的所有文件将被打包到
        应用程序映像。

## 用于创建应用程序启动器的选项:

```  --add-launcher <launcher name=<file path ```

        启动程序的名称，以及包含的属性文件的路径
        键值对的列表
        (绝对路径或相对于当前目录)
        键"module"， "main-jar"， "main-class"，
        “参数”，“java选项”，“应用程序版本”，“图标”，和
        可以使用“win-console”。
        这些选项被添加到原始选项中，或用于覆盖原始选项
        命令行选项，以构建额外的备用启动程序。
        主应用程序启动程序将从命令行构建
        选项。可以使用其他替代发射装置来建造
        此选项和此选项可以多次使用
        建造多个额外的发射器。

```  --arguments <main class arguments ```

        如果没有命令，传递给主类的命令行参数
        行参数被提供给启动器
        此选项可以多次使用。

```  --java-options <java options ```

        传递给Java运行时的选项此选项可以多次使用。

```  --main-class <class name ```

        要执行的应用程序主类的限定名。该选项只能在指定了——main-jar的情况下使用。

```  --main-jar <main jar file ```

        应用程序的主JAR;可以指定——module或——main-jar选项，但不能同时指定两个。

```  --module -m <module name[/<main class] ```

        这个模块必须位于模块路径上。
        当指定此选项时，主模块将链接到Java运行时映像中。可以指定——module或——main-jar选项，但不能同时指定。


## 用来创建应用程序启动程序的与平台相关的选项：

```  --win-console ```

        为应用程序创建控制台启动程序，应当为需要控制台交互的应用程序指定。

## Options for creating the application package:
```  --about-url <url ```

        应用程序主页的URL

```  --app-image <directory path ```

        所使用的预定义应用程序映像的位置构建可安装包(绝对路径或相对于当前目录)

```  --file-associations <file path ```

        包含键值对列表的Properties文件的路径
        (绝对路径或相对于当前目录)
        按键"extension"， "mime-type"， "icon"和"description"
        可以用来描述关联。
        此选项可以多次使用。

```  --install-dir <directory path ```

        默认安装位置下面的相对子路径

```  --license-file <file path ```

        license文件的路径
        (绝对路径或相对于当前目录)

```  --resource-dir <directory path ```

        覆盖jpackage资源的路径
        图标、模板文件和jpackage的其他资源可以
        通过将替换资源添加到此目录而重写。
        (绝对路径或相对于当前目录)

```  --runtime-image <directory path ```

        要安装的预定义运行时映像的路径
        (绝对路径或相对于当前目录)
        选项是创建运行时包时必需的。


## Platform dependent options for creating the application package:

```  --win-dir-chooser ```

        添加一个对话框，允许用户选择其中的目录
        完成产品安装。

```  --win-help-url <url ```

        用户可以在此获取更多信息或技术支持

```  --win-menu ```

        请求为此应用程序添加开始菜单快捷方式

```  --win-menu-group <menu group name ```

        开始菜单组这个应用程序被放置

```  --win-per-user-install ```

        请求在每个用户的基础上执行安装

```  --win-shortcut ```

        请求为此应用程序添加桌面快捷方式

```  --win-shortcut-prompt ```

        添加一个对话框，允许用户选择快捷方式
        将由安装程序创建。

```  --win-update-url <url ```

        可用的应用程序更新信息的URL

```  --win-upgrade-uuid <id string ```

        与此包的升级关联的UUID
