FROM node:latest
WORKDIR "/app"
# when dependencies don't change, cached ones used if prior image has saved
COPY ./package.json ./
RUN npm install
# copy index.js, crawlController.js etc
COPY . .
CMD ["npm", "run", "start"]