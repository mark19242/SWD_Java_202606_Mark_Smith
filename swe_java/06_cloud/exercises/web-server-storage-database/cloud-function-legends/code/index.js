// Cloud Function for getting the legends from Cloud Datastore legends kind

const {Datastore} = require('@google-cloud/datastore');

// Instantiates a client
const datastore = new Datastore();

// Retrieves records from kind=legends from Cloud Datastore
// Ignores all body and method in the request
exports.legends = async (req, res) => {
  try {
    const query = datastore.createQuery('legends');
    const [legends] = await datastore.runQuery(query);

    // The get operation returns an empty dictionary for non-existent entities
    // We want to throw an error instead
    if (!legends) {
      throw new Error(`No entity found for kind of legends ${key.path.join('/')}.`);
    } else {
      console.log("Legends:", JSON.stringify(legends));
    }

    res.set('Access-Control-Allow-Origin', '*');  // Cloud Compute and Cloud Functions are different domains
    res.status(200).send(legends);
  } catch (err) {
    console.error(new Error(err.message)); // Goes to stackdriver errors
    res.status(500).send(err.message);
  }
};
