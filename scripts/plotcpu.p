set loadpath '/vagrant'
set output '/vagrant/reports/report_cpu.png'
set terminal pngcairo  font 'Times-Roman,8' size 800,600
set title "CPU usage"
set xlabel "Time"
set ylabel "%"
set xdata time   
set timefmt "%Y%m%d %H:%M:%S" 
set format x "%H:%M"   
set yrange [0:100]    
plot "/vagrant/stats/stat.dat" using 1:38 title 'Cpu 0' with lines, "/vagrant/stats/stat.dat" using 1:48 title 'Cpu 1' with lines