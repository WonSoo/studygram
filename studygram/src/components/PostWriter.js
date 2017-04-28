import React, {Component, PropTypes} from 'react';
import next from '../img/next.svg'
import Slider from './Slider';

const propTypes = {};
const defaultProps = {};
class PostWriter extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="Card Post-Writer">
                <Slider showArrow={true}>
                    <div className="Slide">
                        <div className="Title-Input-Div">
                            <label className="Write-Content-Label"></label>
                            <p>강렬한 제목을 입력하세요.</p>
                        </div>
                        <input className="Title-Input" type="text" placeholder="제목을 입력하세요."></input>
                    </div>
                    <div className="Slide">
                        <div className="Title-Input-Div">
                            <label className="Write-Content-Label"></label>
                            <p>무엇을 알게 되었나요?</p>
                        </div>
                        <textarea className="Title-Input"></textarea>
                    </div>
                    <div className="Slide">
                        <div className="Title-Input-Div">
                            <label className="Write-Content-Label"></label>
                            <p>태그를 달아주세요!</p>
                        </div>
                        <br></br>
                        <input className="Title-Input" type="text" placeholder="#js #react #css"></input>
                        <input type="button" placeholder="post" value={'전송'}></input>
                    </div>
                </Slider>

                {/* <textarea className="Write-Content"></textarea> */}
            </div>
        );
    }
}
PostWriter.propTypes = propTypes;
PostWriter.defaultProps = defaultProps;
export default PostWriter;
