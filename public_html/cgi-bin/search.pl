#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $search = $query->param('search-box');
print("Content-type: text/html\n");

print <<EndofHTML;
  <html>
   <body>
    <table width="100%" height="80%">
EndofHTML

$search =~ s/^\s*(\S*)\s*$/$1/ if defined($search);
$search =~ s/;|>|>>|<|\*|\?|\&|\|//g if defined($search);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search";
$cmd = "$cmd $search" if defined($search);
system($cmd);

print <<EndofHTML;
    </table>
   </body>
  </html>
EndofHTML
