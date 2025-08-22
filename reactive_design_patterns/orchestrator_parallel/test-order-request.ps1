# URL of the endpoint
$Url = "http://localhost:8080/parallel-orchestrator/order"

# JSON body matching OrderRequest DTO
$JsonBody = '{
    "userId": 2,
    "productId": 51,
    "quantity": 1
}'

# Define headers as a hashtable
$Headers = @{
    "Content-Type" = "application/json"
}

# Send POST request
$response = Invoke-WebRequest -Uri $Url -Method Post -Body $JsonBody -Headers $Headers

# Print response
$response.Content
