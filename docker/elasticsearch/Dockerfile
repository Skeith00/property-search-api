# Use the official Elasticsearch image
FROM docker.elastic.co/elasticsearch/elasticsearch:8.10.0

# Copy setup files
COPY mapping.json /usr/share/elasticsearch/config/mapping.json
COPY data.json /usr/share/elasticsearch/config/data.json
COPY setup.sh /usr/share/elasticsearch/config/setup.sh

# Run setup script on container start
CMD ["sh", "/usr/share/elasticsearch/config/setup.sh"]
