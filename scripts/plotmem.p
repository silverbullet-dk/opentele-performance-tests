set loadpath '/vagrant'
set output '/vagrant/reports/report_mem.png'
set terminal pngcairo font 'Times-Roman,8' size 800,600
set title "Memory"
set xlabel "Time"
set ylabel "GB"
set xdata time   
set timefmt "%Y%m%d %H:%M:%S" 
set format x "%H:%M"  
#set yrange [0:4000000]
plot "./stats/stat.dat" using 1:(($3)/1000000) title 'Total' with lines, "./stats/stat.dat" using 1:(($4)/1000000) title 'Used' with lines, "./stats/stat.dat" using 1:(($5)/1000000) title 'Free' with lines, "./stats/stat.dat" using 1:(($8)/1000000) title 'Cached' with lines