# Bingo Generator

## Abstract

Use this bingo generator to create bingo tickets. It can be used to create multiple bingo tickets from a set of possible
bingo terms. The bingo generator will produce a compile-ready <code>bingo.tex</code> LaTex file, which can be
transformed into a printable <code>bingo.pdf</code> with a single command.

Never has it been easier to create multiple bingo tickets.

## Table of contents

[When to use](#when-to-use) </br>
[How to use](#how-to-use)

## When to use

A fun way to play bingo is with friends when watching a show or a speech. Whenever the term on your bingo ticket is used
in the show or speech you get to cross it off.

Alternatively you can also use numbers as the terms and randomly pick them for a classic game of bingo.

## How to use

With this bingo generator you can create as many bingo tickets as you would like (technically it is limited to <code>
Integer.MAX_VALUE</code>).

1. Build the project with `mvn clean install`
2. Under the `bingo-generator/target` folder you will find a `bingo-generator-1.0-SNAPSHOT.jar`
   file
3. On the command line run:

```
java -jar <pathOfJar> <numberTickets> <rowsPerTicket> <path> <terms>
```

* Where `pathOfJar` is the path of the created jar file,
* `numberOfTickets` is an integer number specifying the amount of bingo tickets that will be created,
* `rowsPerTicket` is an integer number specifying the number of rows per bingo tickets,
* `path` is the path to the location where the bingo ticket file should be created,
* `terms` are the possible terms for all bingo tickets seperated by single spaces **or** the path of a file containing
  all terms. If you want to provide a file with the terms, then the terms in the file have to be written one on each
  line. There is no separator needed.
  > List of terms containing 4 entries:
  ```text
  firstTerm
  secondTerm
  Two words
  as one
  ```
### Installing compiler for creating pdf file

The bingo tickets are written into a <code>bingo.tex</code> file, which is a [LaTex](https://www.latex-project.org)
file. In order to create the pdf file a LaTex compiler is needed. For this have a look
at [TexLive](https://www.tug.org/texlive/). For macOS, you can
use [MacTex](https://www.tug.org/mactex/mactex-download.html).

4. Using a LaTex compiler, compile the <code>bingo.tex</code> file.
    * For TexLive and MacTex you can use
   ```
   pdflatex <path_to_bingo.tex>
   ``` 
