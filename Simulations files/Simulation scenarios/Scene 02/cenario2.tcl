set ns [new Simulator]

$ns color 1 Yellow
$ns color 2 Green
$ns color 3 Blue

set nf [open out_cen2.nam w]
$ns namtrace-all $nf

set nf2 [open out_cen2.tr w]
$ns trace-all $nf2

set f0 [open out_cen2FTP-bw.tr w]
set f1 [open out_cen2CBR1-bw.tr w]
set f2 [open out_cen2CBR2-bw.tr w]

proc finish {} {
        global ns nf
        $ns flush-trace
        close $nf
        exec nam out_cen2 &
	exec xgraph out_cen2FTP-bw.tr out_cen2CBR1-bw.tr out_cen2CBR2-bw.tr &
        exit 0
}
proc record {} {
        global sink f0 f1 f2 sinkudp sinkudp2
	set ns [Simulator instance]
        set time 0.100
        set bw0 [$sink set bytes_]
	set bw1 [$sinkudp set bytes_]
	set bw2 [$sinkudp2 set bytes_]
        set now [$ns now]
        puts $f0 "$now [expr $bw0/$time*8/1000000]"
	puts $f1 "$now [expr $bw1/$time*8/1000000]"
	puts $f2 "$now [expr $bw2/$time*8/1000000]"
        $sink set bytes_ 0
	$sinkudp set bytes_ 0
	$sinkudp2 set bytes_ 0
        $ns at [expr $now+$time] "record"
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$ns duplex-link $n0 $n1 2Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 20ms SFQ
$ns duplex-link $n1 $n3 2Mb 10ms DropTail
$ns duplex-link $n1 $n4 1Mb 20ms SFQ
$ns duplex-link $n4 $n5 2Mb 10ms DropTail
$ns duplex-link $n2 $n5 2Mb 10ms DropTail

$ns duplex-link-op $n1 $n2 queuePos 0.5

set tcp [new Agent/TCP]
$tcp set class_ 2
$ns attach-agent $n0 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n5 $sink
$ns connect $tcp $sink
$tcp set fid_ 1

set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP

set udp [new Agent/UDP]
$ns attach-agent $n0 $udp
set null [new Agent/Null]
$ns attach-agent $n5 $null
$ns connect $udp $null
$udp set fid_ 2
set sinkudp [new Agent/LossMonitor]
$ns attach-agent $n5 $sinkudp
$ns connect $udp $sinkudp

set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp
$cbr set type_ CBR
$cbr set packet_size_ 512
$cbr set rate_ 2mb
$cbr set random_ false

set udp2 [new Agent/UDP]
$ns attach-agent $n3 $udp2
set null2 [new Agent/Null]
$ns attach-agent $n5 $null2
$ns connect $udp2 $null2
$udp2 set fid_ 3
set sinkudp2 [new Agent/LossMonitor]
$ns attach-agent $n5 $sinkudp2
$ns connect $udp2 $sinkudp2

set cbr2 [new Application/Traffic/CBR]
$cbr2 attach-agent $udp2
$cbr2 set type_ CBR
$cbr2 set packet_size_ 512
$cbr2 set rate_ 2mb
$cbr2 set random_ false


$ns at 0.0 "record"
$ns at 0.5 "$ftp start"
$ns at 3.0 "$cbr start"
$ns at 4.0 "$cbr2 start"
$ns at 7.0 "$cbr2 stop"
$ns at 7.0 "$cbr stop"
$ns at 9.0 "$ftp stop"
$ns at 10.0 "finish"

$ns run