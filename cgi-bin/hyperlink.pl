#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $isbn = $query->param('isbn');

print("Content-type: text/html\n\n");

my $compile = "/usr/bin/javac Search.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search ";
$cmd = $cmd . $isbn;
system($cmd);