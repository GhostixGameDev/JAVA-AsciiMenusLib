<h1>JAVA-asciimenusLib</h1>

Automatic ascii menus library for my school JAVA assignments. <br />
In order for this  to work you will need:<br />
<ol>
    <li>A run method with a InputStream parameter in all the classes you want the Main Menu to execute.</li>
    <li>One instance of MainMenu in... well. The main menu</li>
</ol>
1) 

=======================================================================<br />
MainMenu:<br />
The MainMenu class prints a Menu of options that executes the run methods of all the objects you give it in the constructor.<br />

You will have to make an Array of type Object[] that stores all your options (Remember that all of them have to have a public Run method with a InputStream parameter).<br />
Then you will have to make a new instance of MainMenu<br />
The parameters for MainMenu are:<br />
<ul>
    <li>The title (String).</li>
    <li>The pattern of the options (String). For example "Object ", "Option ", "Class ".<br />
It will look something like<ol>
                <li>Object 1.</li>
                <li>Object 2.</li>
                <li>Object 3.</li>
            </ol>
    </li>
    <li>The options that it will execute (Object[]).</li>
</ul>
<br />
MainMenu constructors:<br />
MainMenu(Object[] options);<br />
MainMenu(String name, Object[] options);<br />
MainMenu(String name, String pattern, Object[] options);<br />
<br />
MainMenu methods:<br />
run(InputStream inputStream);<br />
<br />
==========================================================================<br />
SubMenu:<br />
The SubMenu class prints a Menu of options that executes all the methods of the object it is inside of.
You can make customOptions if there is one method that cant be called with the SubMenu handling of parameters (Everything not readable via ConsoleReader).<br />
And also you can hide methods from the options list.<br />
<br />
<br />
You will have to add this on your run methods if you want to use it.<br />
First you have to define an int for the option.<br />
Second you have to make an Array (String[]) of the customOptions you want to add if you need something not contemplated in the submenu. Ex: Object parameters, Method parameters, Class parameters. If you dont want any just make the array with {""} as the only value.<br />
Then you will have to make an Array (String[]) of the HiddenOptions you want to add. If you dont want any just make the array with {""} as the only value or dont pass it into the constructor<br />

We make an instance of our SubMenu. The parameters are:
<ol>
    <li>Title (String).</li>
    <li>Object to extract the methods (this).</li>
    <li>If there is custom options (Boolean).</li> 
    <li>The custom options array (String[]).</li>
    <li>The hidden options array (String[]).</li>
</ol>

<br />
After that you will have to set-up the loop for the run method.<br />
<br />
while([option]!=[yourMenu].getExit()){<br />
    option = [yourMenu].run([Scanner]);<br />
    *****Custom options here if you have any******<br />
}<br />
SubMenu constructors: <br />
SubMenu(Object object);<br />
SubMenu(String name, Object object);<br />
SubMenu(String name, Object object, String[] customOptions);<br />
SubMenu(String name, Object object, String[] customOptions, String[] hiddenOptions);<br />
<br />
SubMenu methods:<br />
int run(InputStream inputStream);<br />
<br />
<br />
Interfaces:<br />
<br />
ITitlePrinter<br />
<br />
Methods:<br />
printTitle(String name);<br />


