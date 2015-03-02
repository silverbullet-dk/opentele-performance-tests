set loadpath '/vagrant'
set output '/vagrant/reports/report_disk.png'
set terminal pngcairo  font 'Times-Roman,8' size 800,600
set title "Disk usage"
set xlabel "Time"
set ylabel "Count / KB"
set xdata time   
set timefmt "%Y%m%d %H:%M:%S"
set format x "%H:%M" 
set yrange [0:10000]        
plot "./stats/stat.dat" using 1:32 title 'KB reads' with lines, "./stats/stat.dat" using 1:29 title 'Reads' with lines, "./stats/stat.dat" using 1:30 title 'Writes' with lines, "./stats/stat.dat" using 1:33 title 'KB Writes' with lines