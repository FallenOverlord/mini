# Colaborating with a team using git

## Tracking and Committing
`git status`to check for uncommitted changes  
`git add <file>` to add new files to Git tracking  
`git commit -m "<description>"` to commit the changes

## Branching and Pulling
`git branch` to check the name of the branch, e.g. `* master`means you are on the master branch.  
If there's changes in the remote repository that your local copy doesn't, you may see the following error.  
```bash
$ git push origin master
To ug251.eecg.utoronto.ca:/groups/ECE297S/cd-099/mapper_repo
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'ug251.eecg.utoronto.ca:/groups/ECE297S/cd-099/mapper_repo'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

git pull -r
```
remediate it by pulling the latest chages before pushing: `git pull origin master --rebase`  
now push your commits to the repository: `git push origin master`  



## Summary
workflow
```bash
git status
git add libstreetmap/src/m1.cpp
git commit -m " building loadmap and closemap funcs on m1.cpp "
```