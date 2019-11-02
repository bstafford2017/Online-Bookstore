#!/usr/bin/perl
use strict;
use CGI; 
my $query = new CGI;

my $compile = "/usr/bin/javac Hyperlink.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Hyperlink ";
$cmd = $cmd . $isbn;
system($cmd);