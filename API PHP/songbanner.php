<?php
	require "connect.php";
	$query = "SELECT quangcao.id, quangcao.Hinhanh, quangcao.Noidung, 
					quangcao.idbaihat, baihat.TenBaiHat, baihat.HinhBaiHat
			  FROM baihat INNER JOIN quangcao ON quangcao.idbaihat = baihat.idBaiHat
			  WHERE quangcao.idbaihat = baihat.idBaiHat";
	$data = mysqli_query($con,$query);

	class Quangcao{
		function Quangcao($idquangcao,$hinhanh,$noidung,$idbaihat,$tenbaihat,$hinhbaihat){
			$this->IdQuangCao = $idquangcao;
			$this->Hinhanh = $hinhanh;
			$this->Noidung = $noidung;
			$this->IdBaiHat = $idbaihat;
			$this->TenBaiHat = $tenbaihat;
			$this->HinhBaiHat = $hinhbaihat;
		}
	}

	$mangquangcao = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangquangcao,new Quangcao($row['id']
												,$row['Hinhanh']
												,$row['Noidung']
												,$row['idbaihat']
												,$row['TenBaiHat']
												,$row['HinhBaiHat']));
		# code...
	}
	echo json_encode($mangquangcao);
?>