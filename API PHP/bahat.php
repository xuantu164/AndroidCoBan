<?php
	require "connect.php";

	class Baihat{
		function Baihat($idbaihat,$tenbaihat,$hinhbaihat,$casi,$linkbaihat,$luotthich){
			$this->Idbaihat = $idbaihat;
			$this->Tenbaihat = $tenbaihat;
			$this->Hinhbaihat = $hinhbaihat;
			$this->Casi = $casi;
			$this->Linkbaihat = $linkbaihat;
			$this->Luotthich = $luotthich;
		}
	}
?>