#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $title = $query->param('book-title');
my $price = $query->param('price');
my $isbn = $query->param('isbn');
my $subjects = $query->param('subjects');
my @subject = split(' ', $subjects);
my $allSubjects = "";
for my $i (@subject){
    $allSubjects = $allSubjects . " " . $i;
}

if(!defined $title && !defined $price && !defined $isbn && !defined $subjects){
    exit(0);
}

$title =~ s/^\s*(\S*)\s*$/$1/;
$title =~ s/;|>|>>|<|\*|\?|\&|\|//g;
$price =~ s/^\s*(\S*)\s*$/$1/;
$price =~ s/;|>|>>|<|\*|\?|\&|\|//g;
$isbn =~ s/^\s*(\S*)\s*$/$1/;
$isbn =~ s/;|>|>>|<|\*|\?|\&|\|//g;
#$allSubjects =~ s/^\s*(\S*)\s*$/$1/;
#$allSubjects =~ s/;|>|>>|<|\*|\?|\&|\|//g;

print($title . $price . $isbn . $subjects);

my $compile = "/usr/bin/javac Create.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Create ";
$cmd = $cmd . " " . $isbn . " " . $title . " " . $price . " " . $allSubjects;
print("Content-type: text/html\n\n");
system($cmd);
print($cmd);