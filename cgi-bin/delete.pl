#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;

my $compile = "/usr/bin/javac Delete.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Delete " . $isbn;
my @names = $query->param;
foreach my $name (@names) {
    $cmd = $cmd . " " . $name . " " . $query->param($name);
}
system($cmd);