#!/usr/bin/perl
use strict;
use CGI; 
my $query = new CGI;

my $compile = "/usr/bin/javac ListBooks.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom ListBooks ";
system($cmd);

my @names = $query->param;
foreach my $name (@names) {
    $cmd = $cmd . " " . $name . " " . query->param($name);
}