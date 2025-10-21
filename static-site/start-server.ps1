$port = 8080
$directory = "C:\Users\Miles\Documents\IBM\java-database-capstone\static-site"

Add-Type -AssemblyName System.Net.Http
$listener = New-Object System.Net.HttpListener
$listener.Prefixes.Add("http://localhost:$port/")
$listener.Start()

Write-Host "Server started at http://localhost:$port"
Write-Host "Serving directory: $directory"
Write-Host "Press Ctrl+C to stop the server"

try {
    while ($listener.IsListening) {
        $context = $listener.GetContext()
        $request = $context.Request
        $response = $context.Response
        
        $requestPath = $request.Url.LocalPath
        if ($requestPath -eq "/" -or $requestPath -eq "") {
            $requestPath = "/index.html"
        }
        
        $filePath = Join-Path $directory $requestPath.TrimStart('/')
        
        if (Test-Path $filePath -PathType Leaf) {
            $content = Get-Content $filePath -Raw -Encoding UTF8
            $contentType = switch ([IO.Path]::GetExtension($filePath)) {
                ".html" { "text/html; charset=utf-8" }
                ".css" { "text/css" }
                ".js" { "application/javascript" }
                default { "text/plain" }
            }
            
            $response.ContentType = $contentType
            $buffer = [System.Text.Encoding]::UTF8.GetBytes($content)
            $response.ContentLength64 = $buffer.Length
            $response.OutputStream.Write($buffer, 0, $buffer.Length)
        } else {
            $response.StatusCode = 404
            $response.StatusDescription = "Not Found"
            $notFoundContent = "404 - File not found: $requestPath"
            $buffer = [System.Text.Encoding]::UTF8.GetBytes($notFoundContent)
            $response.ContentLength64 = $buffer.Length
            $response.OutputStream.Write($buffer, 0, $buffer.Length)
        }
        
        $response.OutputStream.Close()
    }
} finally {
    $listener.Stop()
}