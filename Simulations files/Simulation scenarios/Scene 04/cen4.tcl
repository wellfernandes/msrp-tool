set ns [new Simulator]

$ns color 1 Yellow
$ns color 2 Green

set nf [open out-quest9.nam w]
$ns namtrace-all $nf

set nf2 [open out-quest9.tr w]
$ns trace-all $nf2

set f0 [open out-quest9-bw.tr w]

proc finish {} {
        global ns nf nf2 f0
        $ns flush-trace
        close $nf
	close $nf2
	close $f0
        exec nam out-quest9 &
	exec xgraph out-quest9-bw.tr &
        exit 0
}
proc record {} {
        global sink f0
	set ns [Simulator instance]
        set time 0.100
        set bw0 [$sink set bytes_]
        set now [$ns now]
        puts $f0 "$now [expr $bw0/$time*8/1000000]"
        $sink set bytes_ 0
        $ns at [expr $now+$time] "record"
}
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

$ns duplex-link $n0 $n2 2Mb 10ms DropTail
$ns duplex-link $n1 $n2 2Mb 10ms DropTail
$ns duplex-link $n2 $n3 2Mb 20ms DropTail

$ns duplex-link-op $n2 $n3 queuePos 0.5

set tcp [new Agent/TCP]
$tcp set class_ 2
$ns attach-agent $n0 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n3 $sink
$ns connect $tcp $sink
$tcp set fid_ 1

set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP

set udp [new Agent/UDP]
$ns attach-agent $n1 $udp
set null [new Agent/Null]
$ns attach-agent $n3 $null
$ns connect $udp $null
$udp set fid_ 2

set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp
$cbr set type_ CBR
$cbr set packet_size_ 3000
$cbr set rate_ 1.8mb
$cbr set random_ false

$ns at 0.0 "record"
$ns at 0.1 "$cbr start"
$ns at 1.0 "$ftp start"
$ns at 3.5 "$cbr stop"
$ns at 8.0 "$ftp stop"
$ns at 10.0 "finish"

$ns run