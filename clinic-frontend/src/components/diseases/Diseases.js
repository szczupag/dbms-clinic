import React, {Component} from 'react';
import constants from '../../constants/pages';
import Disease from './Disease';
import Select from 'react-select';

class Diseases extends Component {
    constructor(props){
        super(props)
        this.state = {
            diseases: [],
            sortBy: null,
        }
        this.sortChangeHandler = this.sortChangeHandler.bind(this);
    }

    componentDidMount () {
        this.props.getHandler(constants.DISEASES);
        this.setState({
            diseases: this.props.diseases
        })
        console.log(this.state.diseases)
    }

    componentWillReceiveProps(newProps){
        this.setState({
            diseases: newProps.diseases
        })
    }

    sortChangeHandler(selectedSort){
        switch(selectedSort.value){
            case 'name':
                this.state.diseases.sort((a,b)=>a.name>b.name);
                break;
            default:
                break;
        }
        this.setState({
            sortBy: selectedSort
        })
    }

    render(){
        return(
            <div className="diseases">
                <div className="page-title">
                    <span>Diseases</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.HOME)}
                    >Back</button>
                </div>
                <div className="options">
                    <button 
                        className="controls-btn add"
                        onClick={()=>this.props.changePanel(constants.NEW_DISEASE)}
                    >Add new disease</button>
                </div>
                <div className="sort">
                    <Select
                        placeholder="Sort by"
                        className="selectBox sort"
                        options={[
                            { value: 'name', label: 'name' }
                        ]}
                        value={this.state.sortBy}
                        onChange={this.sortChangeHandler}
                    />
                </div>
                <div className="elements">
                {
                    this.state.diseases.map( (disease, index) => {
                        return <Disease
                            key={index} 
                            data={disease}
                            deleteHandler={this.props.deleteHandler}
                            editItemHandler={this.props.editItemHandler}
                            changePanel={this.props.changePanel}
                            />
                    })
                }
                </div>
            </div>
        )
    }
}

export default Diseases;