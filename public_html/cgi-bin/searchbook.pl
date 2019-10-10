#!/usr/bin/perl
use CGI; 
$query = new CGI;
$search = $query->param('search-box');
print $query->header("text/javascript");
print $search

$search =~ s/^\s*(\S*)\s*$/$1/;
$search =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom SearchBook ";
$cmd = join $search, " ";

system($cmd);

print($cmd);