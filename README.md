# Bingo Generator

## Abstract

Use this bingo generator to create bingo tickets. It can be used to create multiple bingo tickets from a set of possible
bingo terms. The bingo generator will produce a compile-ready <code>bingo.tex</code> file which you can transform into a
printable <code>bingo.pdf</code> with a single command.

Never has it been so easy to create multiple bingo tickets.

## Table of contents

[When to use](#when_to_use) </br>
[How to use](#how_to_use)

## When to use

A fun way to play bingo is with friends when watching a show or a speech. Whenever the term on your bingo ticket is used
in the show or speech you get to cross it off.

Alternatively you can also use numbers as the terms and randomly pick them for a classic game of bingo.

## How to use

With this bingo generator you can create as many bingo tickets as you would like (technically it is limited by <code>
Integer.MAX_VALUE</code>).

1. Create a list of strings witch contains all possible bingo terms.
2. Call a <code>BingoTicketGenerator#generateTickets</code> method and specify all desired options.
3. Use the <code>LatexFileWrite#writeToFile</code> method to write the created bingo tables to a file. Specify the path
   where the
   <code>bingo.tex</code> file should be created.

### Installing compiler for creating pdf file

The bingo tickets are written into a <code>bingo.tex</code> file, which is a [LaTex](https://www.latex-project.org)
file. In order to create the pdf file a LaTex compiler is needed. For this have a look
at [TexLive](https://www.tug.org/texlive/). For MacOS you can
use [MacTex](https://www.tug.org/mactex/mactex-download.html).

4. Using a LaTex compiler, compile the <code>bingo.tex</code> file.
