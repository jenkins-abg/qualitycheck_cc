@ECHO OFF
:: This batch file, pulls-out data from the GitHub
:: Author: Paul Ryan A Dedumo
TITLE GitHub Pull
setlocal
:PROMPT
SET /P AREYOUSURE=Do you want to pushGit Repo (Y/[N])?
IF /I "%AREYOUSURE%" NEQ "Y" GOTO END

	ECHO Please wait... Internet connection.
	:: Checking Internet connection.
	ECHO ============================
	ECHO INTERNET CONNECTION INFO
	ECHO ============================
	ping	google.com -n 1 -w 1000
	IF errorlevel 1 ( 
		ECHO No internet connection
	)else (
		ECHO Connected...
		::Targeting dir of gitLocalRepo
		cd "C:\work\GitLocalRepo"
		git init
		git status
		git add .
		git commit -F C:\work\commitMsg.txt
		git fetch --all
		git push -u origin master
		ECHO Success!
	)
	pause
:END
endlocal