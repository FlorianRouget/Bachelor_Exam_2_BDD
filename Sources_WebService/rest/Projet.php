<?php
require_once("dbcontroller.php");

Class Projet {
	private $projects = array();
	
	public function getAllProject(){

		$query = "SELECT * FROM projet";
		$dbcontroller = new DBController();
		$this->projects = $dbcontroller->executeSelectQuery($query);

		return $this->projects;
	}	
}
?>