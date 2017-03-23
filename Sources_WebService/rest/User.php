<?php
require_once("dbcontroller.php");

Class User {
	private $users = array();
	
	public function getAllUser(){
		$query = "SELECT * FROM user";
		$dbcontroller = new DBController();
		$this->users = $dbcontroller->executeSelectQuery($query);
		return $this->users;
	}
	
	public function getUser($index){
		$query = "SELECT * FROM user WHERE ID_USER = ".index;
		$dbcontroller = new DBController();
		$this->users = $dbcontroller->executeSelectQuery($query);
		return $this->users;
	}
	
	public function addUser($Login, $Password){
		$query = "INSERT INTO `USER`(`LOGIN`,`PASSWORD`) VALUES ('".$Login."','".$Password."')"; 
		$dbcontroller = new DBController();
		$this->users = $dbcontroller->executeSelectQuery($query);
		return $this->users;
	}

}
?>