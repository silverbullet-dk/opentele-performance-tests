# NET version sudo collectl -sCmdn -oT -oc -P --quiet > /vagrant/stats/stat.dat &
mkdir -p /vagrant/stats
sudo collectl -sCmd -oT -oc -P --quiet > /vagrant/stats/stat.dat &
