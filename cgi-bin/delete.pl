#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $isbn = query->param('isbn');

my $compile = "/usr/bin/javac Delete.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Delete " . $isbn;
system($cmd);