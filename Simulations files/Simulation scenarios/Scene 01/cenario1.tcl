##Welliton Fernandes Leal
##Instituto Federal do Paraná
##SCRIPT TCL - SIMULAÇÃO DE REDE

##Inicio padrão da simulação pelo Script TCL
set ns [new Simulator]

##Cor de fluxo para demonstração na ferramenta NAM 
$ns color 1 Yellow

set nf [open out_cen1.nam w]
$ns namtrace-all $nf

set nf2 [open out_cen1.tr w]
$ns trace-all $nf2

set f0 [open out_cen1-bw.tr w]

proc finish {} {
        global ns nf
        $ns flush-trace
        close $nf
        exec nam out_cen1 &
	exec xgraph out_cen1-bw.tr &
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

##Nos da rede
set n0 [$ns node] 
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

##Tipo de conexão - Nó Origem e Nó destino
##Largura da banda - tempo - Politíca de fila
$ns duplex-link $n0 $n1 2Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 20ms DropTail
$ns duplex-link $n1 $n3 2Mb 10ms DropTail
$ns duplex-link $n1 $n4 1Mb 20ms DropTail
$ns duplex-link $n4 $n5 2Mb 10ms DropTail
$ns duplex-link $n2 $n5 2Mb 10ms DropTail

$ns duplex-link-op $n1 $n2 queuePos 0.5

##Configuração dos protocolos que irão rodar
##TCP 
set tcp [new Agent/TCP]
$tcp set class_ 2
$ns attach-agent $n0 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n5 $sink
$ns connect $tcp $sink
$tcp set fid_ 1

##Protocolo FTP
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP

##Inicia a Simulação, grava e finaliza no tempo
##determinado
$ns at 0.0 "record"
$ns at 0.5 "$ftp start"
$ns at 9.0 "$ftp stop"
$ns at 10.0 "finish"

$ns run