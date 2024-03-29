# Exemplo de requisição usando PowerShell

# Limpa o terminal
Clear-Host

# Lê a imagem como bytes
$bytes = [System.IO.File]::ReadAllBytes('C:\dev\projects\imagehandlejava\assets\dc726ec8-fc7f-41ed-9eb8-9e8c66a5f246.jpg')

# Converte os bytes para uma string base64
$base64String = [Convert]::ToBase64String($bytes)

# Define os parâmetros da requisição
$postParams = @{
    imagemAtualBase64 = $base64String
    caminhoImagemNova = 'C:\dev\projects\imagehandlejava\assets\novaImagem.jpg'
    tipoImagem = '.jpg'
    resolucaoVertical = 1680
    resolucaoHorizontal = 1344
}

$postParams = $postParams | ConvertTo-Json

# Faz a requisição POST para a API
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/resize-image" -Method POST -Body $postParams -ContentType "application/json"

# Exibe a resposta da API
$response.Content
