import React, { Component }  from 'react';
import "./Home.css"
import PanelBtn from "../PanelBtn/PanelBtn"

class Home extends Component{
    state = {
        panels: ["doctor", "patient", "visitor"]
    }
    render () {
        var changePanel = this.props.changePanel;
        const panel = this.state.panels.map((panel, index) => {
                return <PanelBtn key={index} title={panel} click={() => changePanel(panel)}/>;
        });
        return(
            <header className="App-header">
                <h1>Go to panel for...</h1>
                <div className="panel-btns-wrapper">
                    {panel}
                </div>
            </header>
        );
    }
}

export default Home;
