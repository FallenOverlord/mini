# Jupyter Book Basics

## 1. Install jupyter book on VS Code
pip install -U jupyter-book

## 2.Check version status
jupyter-book --version

OR

jb --version

## 3. Create a new template
jb create <book_name>/

## 4. Book structure
_config.yml contain title, author and logo info
_toc.yml contain the files and chapters of the book
.md, .ipynb and .rst files are all part of the book content
a Markdown file (markdown.md)
a Jupyter Notebook (.ipynb): a combination of computational content and narrative content
a MyST Markdown Notebook (markdown-notebooks.md)

## 5. Basic Markdown syntaxes
```bash
:::{<name>}
<content>
:::

#<title>
##<section_header>
```

```bash
**<content>** 
```
makes the content bold

## `[<hyperlink_text>](<relative_path_to_linked_file>)` will create a hyperlink

## 6. Building the book
`jb build <book_name>/` will generate a fully functioning HTML site
after changes are incurred and a newer version of HTML site needs to 
be built upon the changed files, you should use the command `jb build --all <book_name>/`
tip: after new content files are added in, remember to add `- file: <file_name>` in
the _toc.yml file

## 7. Create an online repo for jupyter-book
first create a new dir in github: https://github.com/new
tip: ensure that the new dir has both name and descriptions but NO README file
Next, clone the empty dir into local dev env: `git clone https://github.com/<my-
org>/<my-repository-name>`
Now, copy all the jupyter-book content into the newly added empty folder:
`cp -r <jupyter_book_name>/* <newly_cloned_dir_name>/`
Next step is to sync the local and remote repo:
`cd <newly_cloned_dir_name>`
git add ./*
git commit -m "adding my first book!"
git push

## 8. Publish the jupyter-book online
Firstly, pip install ghp-import
Secondly, cd into the `<newly_cloned_dir>`
ghp-import -n -p -f _build/html
see the hosted website at `https://FallenOverlord.github.io/<newly_cloned_dir>/`

## 9. Update the online site
add in the .md file and remember to update the _toc.yml file
use the command `jb build --all mini/`
```bash
cd mini/
git add ./*
git commit -m "adding my first book!"
git push
ghp-import -n -p -f _build/html
```
```{tip}
tip: got to `https://github.com/<user_name>/<project_name>/actions`
to check if the update is through
```


## Jupyter-book Explore More Features

Code blocks, non-executable code
First, add headers

```bash
---
jupytext:
  text_representation:
    extension: .md
    format_name: myst
kernelspec:
  display_name: Python 3
  language: python
  name: python3
---
```


Next,  foramt non-executable code like this
```bash
ssh username@hostname
```

## Summary
### Local Build & Publish
Get to the location that contains the project, mini in my case. Not inside the project.
```bash
jb build --all mini/
cd mini/
git add ./*
git commit -m "adding my first book!"
git push
ghp-import -n -p -f _build/html
```

### Errors and Rectification
Below are some of the errors that I've encountered and fixed.
```bash
 ! [rejected]        main -> main (fetch first)
error: failed to push some refs to 'https://github.com/FallenOverlord/mini'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```
remediation: `git push --force origin main`
