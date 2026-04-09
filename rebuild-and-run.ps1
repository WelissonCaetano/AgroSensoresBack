param(
    [string]$ImageName = 'agro-sensores-api',
    [string]$ContainerName = 'agro-sensores-api',
    [int]$HostPort = 8081,
    [int]$ContainerPort = 8081,
    [switch]$NoCache
)

$ErrorActionPreference = 'Stop'

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $projectRoot

Write-Host "Project root: $projectRoot"
Write-Host "Image: $ImageName"
Write-Host "Container: $ContainerName"

podman info | Out-Null

podman container exists $ContainerName | Out-Null
if ($LASTEXITCODE -eq 0) {
    Write-Host "Removing existing container '$ContainerName'..."
    podman rm -f $ContainerName | Out-Null
}

$buildArgs = @('build', '-t', "localhost/$ImageName", '.')
if ($NoCache) {
    $buildArgs = @('build', '--no-cache', '-t', "localhost/$ImageName", '.')
}

Write-Host 'Building image...'
& podman @buildArgs

Write-Host 'Starting container...'
podman run -d --name $ContainerName -p "${HostPort}:${ContainerPort}" "localhost/$ImageName" | Out-Null

Write-Host ''
Write-Host 'Container started successfully.'
Write-Host "Swagger: http://localhost:$HostPort/swagger-ui.html"
Write-Host "OpenAPI: http://localhost:$HostPort/v3/api-docs"
Write-Host "H2 Console: http://localhost:$HostPort/h2-console"