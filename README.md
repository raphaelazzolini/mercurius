# Mercurius
Mercurius is a Software Products Line for developing e-commerce systems. It has a set of modules implemented with component-based and aspect-oriented techiniques that facilitates to add, remove and modify feateures of the systems.

This is a project from the [Distributed Systems Laboratory](http://www.lsd.ic.unicamp.br/) of the [Institute of Computing](http://ic.unicamp.br/) at [University of Campinas](http://www.unicamp.br/), in Brazil.

Below you find publications about this project:
* [Evolving a Software Products Line for E-commerce Systems: a Case Study](http://dl.acm.org/citation.cfm?id=2797460)

This project is builded with [Maven](https://maven.apache.org/). To build it you must build the [COSMOS*](https://github.com/raphaelazzolini/cosmos) jar first.

The last version of this Software Products Line was tested in the [Wildfly Application Server](http://wildfly.org/) version [8.1.0-Final](http://download.jboss.org/wildfly/8.1.0.Final/wildfly-8.1.0.Final.zip). We provide a pre-configured Virtual Machine (VM), in OVA format, with a Demo Store in the link below:
* [CentOS](https://drive.google.com/file/d/0B_amipaKDpCjZ2M4eFpNWEV1UDA/view?usp=sharing)

This VM was created on [Oracle VM VirtualBox](https://www.virtualbox.org/). You must create a [Host-Only Network](https://www.virtualbox.org/manual/ch06.html#network_hostonly) with the ip address 192.168.56.1 to access the VM.

It has an user **mercurius** with password **mercurius** and a **root** user with password **root**.

To run the application server you must run the command */home/mercurius/wildfly/bin/standalone.sh*.

If you build a new EAR file for the demo store or any other store, you can copy it in */home/mercurius/wildfly/standalone/deployments* for deploying it on the Application Server of the VMs.

The CentOS VM demo store runs on http://192.168.56.3:8080/demostore. You can create new products on http://192.168.56.3:8080/admin.

This application runs with a [MySQL Server](https://www.mysql.com/), so you can access the its database with a MySQL Client.
