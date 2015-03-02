sudo pkill collectl
sleep 4
sed -i '1s/^/# /' /vagrant/stats/stat.dat