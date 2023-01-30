import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Dashboard from 'pages/dashboard';

const routes = () => (
  <>
    <Router>
      <Switch>
        <Route exact path='/' component={Dashboard} />
      </Switch>
    </Router>
  </>
);
export default routes;
