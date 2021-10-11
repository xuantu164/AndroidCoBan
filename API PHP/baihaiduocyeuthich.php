<?php
	require "connect.php";

	class Baihat{
		function Baihat($idbaihat,$tenbaihat,$hinhbaihat,$casi,$linkbaihat,$luotthich){
				$this->IdBaihat = $idbaihat;
				$this->Tenbaihat = $tenbaihat;
				$this->Hinhbaihat = $hinhbaihat;
				$this->Casi = $casi;
				$this->Linkbaihat = $linkbaihat;
				$this->Luotthich = $luotthich;
		}
	}
	$arraycasi = array();
	$query = "SELECT * FROM baihat ORDER BY Luotthich DESC LIMIT 10";
	$data = mysqli_query($con,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraycasi, new Baihat($row['idBaiHat'],
			$row['TenBaiHat'],$row['HinhBaiHat'],$row['CaSi'],$row['LinkBaiHat'],$row['LuotThich']));
	}
	echo json_encode($arraycasi);
?>