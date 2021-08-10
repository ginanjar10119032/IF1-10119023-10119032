-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 10, 2021 at 01:55 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kemahasiswaan_10119023_10119032`
--

-- --------------------------------------------------------

--
-- Table structure for table `history_transaksi`
--

CREATE TABLE `history_transaksi` (
  `no` int(10) NOT NULL,
  `pemasukan` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `list_makanan`
--

CREATE TABLE `list_makanan` (
  `nama_makanan` varchar(30) NOT NULL,
  `harga` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `list_makanan`
--

INSERT INTO `list_makanan` (`nama_makanan`, `harga`) VALUES
('Ayam Bakar', 18000),
('Caramel Macchiato', 25000),
('Chocolate Hazelnut', 15000),
('Chocolate Mint', 15000),
('Lemon Tea', 17000),
('Matcha', 20000),
('Mie Goreng', 12000),
('Nasi Goreng', 14000),
('Rainbow', 12000),
('Vanilla Latte', 12000);

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` int(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `tempat_lahir` varchar(20) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nama`, `tempat_lahir`, `tgl_lahir`, `alamat`) VALUES
(10110001, 'Wongsojoyo Hulin', 'Balikpapan', '1998-01-13', 'Jl Proklamasi No. 56'),
(10110002, 'Sudomo Iwan Lie', 'Bandung', '1998-06-17', 'Jl Gandaria VIII No.2'),
(10110003, 'Suryadi Purnama Setiawan', 'Bandung', '1998-10-12', 'Jl Sunan Kudus No.85'),
(10110004, 'Leony Yenny Sugiarto', 'Semarang', '1998-02-25', 'Jl Genuk Krajan III No.45'),
(10110005, 'Wira Eka Sumadi', 'Bandung', '1997-11-02', 'Jl Guntur Madu No.7');

-- --------------------------------------------------------

--
-- Table structure for table `mata_kuliah`
--

CREATE TABLE `mata_kuliah` (
  `nomor_mk` int(10) NOT NULL,
  `nama_mk` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mata_kuliah`
--

INSERT INTO `mata_kuliah` (`nomor_mk`, `nama_mk`) VALUES
(1001, 'Basis Data 2'),
(1002, 'Rekayasa Perangkat Lunak'),
(1003, 'Sistem Informasi'),
(1004, 'Sistem Operasi');

-- --------------------------------------------------------

--
-- Table structure for table `nilai_mhs`
--

CREATE TABLE `nilai_mhs` (
  `nim` int(10) NOT NULL,
  `nomor_mk` int(10) NOT NULL,
  `absensi` int(2) DEFAULT NULL,
  `tugas1` int(3) DEFAULT NULL,
  `tugas2` int(3) DEFAULT NULL,
  `tugas3` int(3) DEFAULT NULL,
  `uts` int(3) DEFAULT NULL,
  `uas` int(3) DEFAULT NULL,
  `nilai_akhir` int(3) DEFAULT NULL,
  `keterangan` enum('LULUS','Tidak Lulus') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nilai_mhs`
--

INSERT INTO `nilai_mhs` (`nim`, `nomor_mk`, `absensi`, `tugas1`, `tugas2`, `tugas3`, `uts`, `uas`, `nilai_akhir`, `keterangan`) VALUES
(10110001, 1001, 13, 80, 70, 90, 80, 90, 84, 'LULUS'),
(10110002, 1003, 12, 70, 70, 80, 80, 70, 74, 'LULUS'),
(10110003, 1002, 14, 90, 90, 80, 90, 80, 85, 'LULUS'),
(10110004, 1001, 10, 60, 60, 70, 60, 70, 65, 'Tidak Lulus');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `no_meja` int(5) NOT NULL,
  `daftar_pesanan` varchar(30) NOT NULL,
  `jumlah` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`no_meja`, `daftar_pesanan`, `jumlah`) VALUES
(7, 'Caramel Macchiato', 2),
(7, 'Lemon Tea', 3),
(8, 'Lemon Tea', 3),
(8, 'Mie Goreng', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pesanan_total_harga`
--

CREATE TABLE `pesanan_total_harga` (
  `no_meja` int(5) NOT NULL,
  `total_harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesanan_total_harga`
--

INSERT INTO `pesanan_total_harga` (`no_meja`, `total_harga`) VALUES
(7, 101000),
(8, 63000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `history_transaksi`
--
ALTER TABLE `history_transaksi`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `list_makanan`
--
ALTER TABLE `list_makanan`
  ADD PRIMARY KEY (`nama_makanan`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `mata_kuliah`
--
ALTER TABLE `mata_kuliah`
  ADD PRIMARY KEY (`nomor_mk`);

--
-- Indexes for table `nilai_mhs`
--
ALTER TABLE `nilai_mhs`
  ADD PRIMARY KEY (`nim`,`nomor_mk`) USING BTREE,
  ADD KEY `nim` (`nim`),
  ADD KEY `nomor_mk` (`nomor_mk`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`no_meja`,`daftar_pesanan`) USING BTREE,
  ADD KEY `daftar_pesanan` (`daftar_pesanan`),
  ADD KEY `no_meja` (`no_meja`);

--
-- Indexes for table `pesanan_total_harga`
--
ALTER TABLE `pesanan_total_harga`
  ADD PRIMARY KEY (`no_meja`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `history_transaksi`
--
ALTER TABLE `history_transaksi`
  MODIFY `no` int(10) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `nilai_mhs`
--
ALTER TABLE `nilai_mhs`
  ADD CONSTRAINT `nilai_mhs_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `nilai_mhs_ibfk_2` FOREIGN KEY (`nomor_mk`) REFERENCES `mata_kuliah` (`nomor_mk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`no_meja`) REFERENCES `pesanan_total_harga` (`no_meja`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pesanan_ibfk_2` FOREIGN KEY (`daftar_pesanan`) REFERENCES `list_makanan` (`nama_makanan`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
