#Instanciando o Objeto de Simulação
set ns [new Simulator]

#Definindo as Cores
$ns color 1 Blue
$ns color 2 Red

#Abrindo o arquivo de trace para o nam
set nf [open out.nam w]
$ns namtrace-all $nf

#Definindo o Procedimento Finish
proc finish {} {
        global ns nf
        $ns flush-trace
        #Close the NAM trace file
        close $nf
        #Execute NAM on the trace file
        exec nam out.nam &
        exit 0
}

#Criando quatro nós
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

#Criando os links entre os nós
$ns duplex-link $n0 $n2 2Mb 10ms DropTail
$ns duplex-link $n1 $n2 2Mb 10ms DropTail
$ns duplex-link $n2 $n3 1.7Mb 20ms DropTail

#Configurando o tempo de espera entre o link para 10
$ns queue-limit $n2 $n3 10

#Configurando uma Conexão TCP
set tcp [new Agent/TCP]
$tcp set class_ 2
$ns attach-agent $n0 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n3 $sink
$ns connect $tcp $sink
$tcp set fid_ 1

#Configurando uma aplicação FTP para o TCP
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP


#Configurando uma conexão UDP
set udp [new Agent/UDP]
$ns attach-agent $n1 $udp
set null [new Agent/Null]
$ns attach-agent $n3 $null
$ns connect $udp $null
$udp set fid_ 2

#Configurando o CBR para o UDP
set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp
$cbr set type_ CBR
$cbr set packet_size_ 1000
$cbr set rate_ 1mb
$cbr set random_ false


#Agendamento de inicio e fim das conexões
$ns at 0.1 "$cbr start"
$ns at 1.0 "$ftp start"
$ns at 4.0 "$ftp stop"
$ns at 4.5 "$cbr stop"

#Chama o método finish aos 5 segundos
$ns at 5.0 "finish"

#Executa a Simulação
$ns run

