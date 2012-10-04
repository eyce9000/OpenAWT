COPYRIGHT NOTICE
----------------
Many of the java.awt.geom classes used in this library are marked as GPL by the OpenJDK. However, the classes that you will use, such as org.openawt.Shape have a classpath copyright exception. This means you can build against these classes without needing to make your code GPL as well. Where not indicated, all code should be considered GPLv2 for the time being. We will try to move what we can to a less restrictive copyright over time.

OpenAWT
-------
OpenAWT is a fork of the OpenJDK java.awt.geom libraries and everything necessary to use java.awt shapes, without needing to be in the JDK environment. This means it is compatible with Android as well as standard Java. OpenAWT provides UI code for painting shapes both to java.awt.Graphics2D as well as android.graphics.Canvas.

OpenAWT also provides a simple SVG library for rendering shapes and drawings to SVG files, including simple transforms and styling. You can use these same SVG shapes to draw to Graphics2D and Canvas, meaning that you can make screen representations identical to SVG representations. Handy if you want to make a drawing program!
