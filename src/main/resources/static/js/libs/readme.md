
jquery and jquery ui are pulled via webjars. See webjars.org
check your .m2 maven repository under org.webjars. the js resources are there
and the web server is   read from the meta-inf
folders in the jars, that is server from jar files.