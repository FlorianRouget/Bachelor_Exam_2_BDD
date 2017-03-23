<?php
require_once("SimpleRest.php");
require_once("User.php");
require_once("Projet.php");
		
class RestHandler extends SimpleRest {

	function getAllUsers() {	

		$user = new User();
		$rawData = $user->getAllUser();

		if(empty($rawData)) {
			$statusCode = 404;
			$rawData = array('error' => 'No users found!');		
		} else {
			$statusCode = 200;
		}

		$requestContentType = 'application/json';//$_POST['HTTP_ACCEPT'];
		$this ->setHttpHeaders($requestContentType, $statusCode);
		
		$result["output"] = $rawData;
				
		if(strpos($requestContentType,'application/json') !== false){
			$response = $this->encodeJson($result);
			echo $response;
		}
	}
	
	function getAllProjects() {	
		$project = new Projet();
		$rawData = $project->getAllProject();

		if(empty($rawData)) {
			$statusCode = 404;
			$rawData = array('error' => 'No projects found!');		
		} else {
			$statusCode = 200;
		}

		$requestContentType = 'application/json';//$_POST['HTTP_ACCEPT'];
		$this ->setHttpHeaders($requestContentType, $statusCode);
		
		$result["output"] = $rawData;
				
		if(strpos($requestContentType,'application/json') !== false){
			$response = $this->encodeJson($result);
			echo $response;
		}
		
	}
	
	function addUsers($log, $pw){
		$user = new User();
		$rawData = $user->addUser($log, $pw);
	}
	
	public function encodeJson($responseData) {
		$jsonResponse = json_encode($responseData);
		return $jsonResponse;		
	}
}
?>