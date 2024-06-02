# JAVA-asciimenusLib
Automatic ascii menus library for my school JAVA assignments.
For this in order to work you will need:
1) A run method with a Scanner parameter in all the classes you want the Main Menu to execute.
2) A MainMenu instance in your... Well. Your main menu.

=======================================================================
MainMenu:
The MainMenu class prints a Menu of options that executes the run methods of all the objects you give it in the constructor.

You will have to make an Array of type Object[] that stores all your options (Remember that all of them have to have a public Run method with a Scanner parameter).
Then you will have to make a new instance of MainMenu
The parameters for MainMenu are:
1) The title (String).
2) The pattern of the options (String). For example "Object ", "Option ", "Class ".
It will look like
1- Object 1.
2- Object 2.
3- Object 3.

3) The options that it will execute (Object[]).

==========================================================================
SubMenu:
The SubMenu class prints a Menu of options that executes all the methods of the object it is inside of.
You can make customOptions if there is one method that cant be called with the SubMenu handling of parameters (Everything not readable via Scanner).
And also you can hide methods from the options list.


You will have to add this on your run methods if you want to use it.
First you have to define an int for the option.
Second you have to make an Array (String[]) of the customOptions you want to add if you need something not contemplated in the submenu. Ex: Object parameters, Method parameters, Class parameters. If you dont want any just make the array with {""} as the only value.
Then you will have to make an Array (String[]) of the HiddenOptions you want to add. If you dont want any just make the array with {""} as the only value.

We make an instance of our SubMenu. The parameters are:
1) Title (String).
2) Object to extract the methods (this).
3) If there is custom options (Boolean).
4) The custom options array (String[]).
5) The hidden options array (String[]).

After that you will have to set-up the loop for the run method.

while(<option>!=<yourMenu>.getExit()){
    option = <yourMenu>.run(<Scanner>);
    *****Custom options here if you have any******
}


