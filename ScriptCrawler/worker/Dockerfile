FROM node:latest
WORKDIR "/app"
# when dependencies don't change, cached ones used if prior image has saved
COPY ./package.json ./
RUN npm install
# copy index.js, intersection.js etc
COPY . .
CMD ["npm", "run", "start"]