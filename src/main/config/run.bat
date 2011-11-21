@ECHO off
start java -cp .\${project.artifactId}-${project.version}-${project.packaging}-with-dependencies.${project.packaging} ${mainPackage}.${mainClass} %1 %2 %3 %4 %5 %6 %7 %8 %9