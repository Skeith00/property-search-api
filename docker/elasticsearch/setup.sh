#!/bin/bash

# Wait for Elasticsearch to start
until curl -s http://localhost:9200; do
  echo "Waiting for Elasticsearch..."
  sleep 5
done

# Create the index with mapping
curl -X PUT "http://localhost:9200/my-index" -H 'Content-Type: application/json' -d @mapping.json

# Load initial data
curl -X POST "http://localhost:9200/my-index/_bulk" -H 'Content-Type: application/json' --data-binary @data.json

echo "Index and data loaded successfully."
