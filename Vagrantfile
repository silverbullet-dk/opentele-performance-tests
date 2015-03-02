# -*- mode: ruby -*-
# vi: set ft=ruby :
VAGRANTFILE_API_VERSION = "2"


Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  
  # base box and URL where to get it if not present
  config.vm.box = "Performance-test"
  config.vm.box_url = "file://opentele-performance-test.box"
  config.vm.network :forwarded_port, host: 4567, guest: 8080
  config.vm.network :forwarded_port, host: 4568, guest: 8090
  
  config.vm.provider "virtualbox" do |v|
    v.memory = 2048
   
  end

   config.vm.provision "shell", inline: "cp /vagrant/opentele-server.war /srv/tomcat/opentele-server/webapps/ROOT.war"
   config.vm.provision "shell", inline: "cp /vagrant/opentele-citizen-server.war /srv/tomcat/opentele-citizen-server/webapps/ROOT.war"
end
