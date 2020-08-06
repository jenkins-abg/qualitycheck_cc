@ECHO OFF
:: This batch file, will initialize and clone the gitHub into localRepo
:: Author: Paul Ryan A Dedumo
TITLE GitHub Clone
setlocal
:PROMPT
SET /P AREYOUSURE=Do you want to cloneGit Repo (Y/[N])?
IF /I "%AREYOUSURE%" NEQ "Y" GOTO END

	ECHO Please wait... Checking system information.
	:: Section 1: OS information.
	ECHO ============================
	ECHO OS INFO
	ECHO ============================
	systeminfo | findstr /c:"OS Name"
	systeminfo | findstr /c:"OS Version"
	systeminfo | findstr /c:"System Type" 
	:: Section 2: Networking information.
	ECHO ============================
	ECHO NETWORK INFO
	ECHO ============================
	ipconfig | findstr IPv4
	ipconfig | findstr IPv6
	:: Section 3: Checking Internet connection.
	ECHO ============================
	ECHO INTERNET CONNECTION INFO
	ECHO ============================
	ping	google.com -n 1 -w 1000
	IF errorlevel 1 ( 
		ECHO No internet connection...
	)else (
		ECHO Connected...
		::Targeting dir of gitLocalRepo
		git clone https://github.com/jenkins-abg/qualitycheck_cc GitLocalRepo
		ECHO Success!
	)
	pause
:END
endlocal