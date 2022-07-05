FROM node:latest
WORKDIR "/app"
COPY ./package.json ./
RUN npm install
# copy index.js, crawlController.js etc
COPY . .
CMD ["npm", "run", "start"]