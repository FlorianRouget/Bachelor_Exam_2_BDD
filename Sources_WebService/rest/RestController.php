<?php
require_once("RestHandler.php");
		
$view = "";
if(isset($_GET["view"]))
	$view = $_GET["view"];

switch($view){ // see .htaccess

	case "user_all":
		$userRestHandler = new RestHandler();
		$userRestHandler->getAllUsers();
		break;
	
	case "project_all":
		$projectRestHandler = new RestHandler();
		$projectRestHandler->getAllProjects();
		break;
	
	case "user_add":
		$userRestHandler = new RestHandler();
		$userRestHandler->addUsers($_POST["add_login"], $_POST["add_pw"]);
		break;
	
	case "" :
		//404 - not found;
		break;
}
?>
